# Zero Credentials in Repository Policy

**Effective Date**: 2026-05-20
**Policy Level**: Mandatory - No Exceptions

## Policy Statement

**No credentials of any kind may be committed to version control, including test credentials.**

This zero-tolerance policy eliminates the entire class of vulnerabilities related to:
- Accidental production deployment of test configurations
- Credential leakage through configuration file copying
- Security risks from "safe" test passwords being similar to production patterns

## Rationale

Even test credentials introduce vulnerabilities when:
1. Configuration files are accidentally deployed to production
2. Test configurations are copied and reused in production contexts
3. Developers use similar patterns for both test and production credentials
4. Configuration files are shared across environments

**The only safe credential is one that doesn't exist in version control.**

## Implementation

### Configuration Files

All configuration files use environment variable placeholders:

```yaml
# ✅ CORRECT - Uses environment variables
spring:
  datasource:
    username: ${POSTGRES_USER}
    password: ${POSTGRES_PASSWORD}

# ❌ WRONG - Hardcoded test credentials (will be blocked by pre-commit hook)
spring:
  datasource:
    username: testuser
    password: testpassword
```

### Gradle Build Script

Test credentials are passed through from environment variables with **NO defaults**:

```gradle
tasks.withType(Test).configureEach {
    // Environment variables - MUST be set via .env file or shell environment
    // NO defaults to enforce zero-credentials policy
    environment 'POSTGRES_DB', System.getenv('POSTGRES_DB')
    environment 'POSTGRES_USER', System.getenv('POSTGRES_USER')
    environment 'POSTGRES_PASSWORD', System.getenv('POSTGRES_PASSWORD')
}
```

**Why NO defaults:**
- Even test defaults in build.gradle create security risks
- Forces explicit credential management from the start
- Prevents accidental use of weak test credentials in production-like environments
- CI/CD must explicitly configure credentials (no hidden defaults)

### Pre-Commit Hook Enforcement

The pre-commit hook blocks ALL credentials in ALL files:

```bash
# Pattern matches any credential-like key-value pair
secret_pattern='(password|passwd|pwd|secret|api[-_]?key|...)[[:space:]]*[:=][[:space:]]*[^<$#][^$#]{2,}'

# Only these file types are exempt:
# - Git hooks (.githooks/*) — may contain pattern examples in comments
# - Copilot instructions (*copilot-instructions.md)
# - Security documentation (*SECURITY*.md, *POLICY*.md, *SECRET*.md, *.adoc)
# - Template files (.example, .sample, .template)
```

## Allowed Patterns

### Documentation Only

These patterns are allowed **ONLY** in:
- Git hooks (`.githooks/*`) — may contain pattern examples in comments
- Copilot instructions (`*copilot-instructions.md`) — contains security policy documentation
- Security documentation files (matching `*SECURITY*.md`, `*POLICY*.md`, `*SECRET*.md`, `*.adoc`)
- Template files (`.example`, `.sample`, `.template`)

```yaml
# ✅ Placeholder syntax
password: <your-password>

# ✅ Environment variable syntax
password: ${PASSWORD}

# ✅ Comment documentation
password: # Set this via environment variable
```

## Development Workflow

### Running Tests Locally

Tests require credentials from a `.env` file or environment variables:

```bash
# Option 1: Export environment variables directly
export POSTGRES_DB=mydb
export POSTGRES_USER=myuser
export POSTGRES_PASSWORD="your-strong-password"
./gradlew test

# Option 2: Create a .env file (gitignored)
cat > .env << 'EOF'
POSTGRES_DB=mydb
POSTGRES_USER=myuser
POSTGRES_PASSWORD=your-strong-password
EOF
```

### CI/CD Configuration

CI/CD systems should inject credentials as environment variables:

```yaml
# GitHub Actions example
- name: Run Tests
  env:
    POSTGRES_PASSWORD: ${{ secrets.TEST_DB_PASSWORD }}
  run: ./gradlew test
```

## Consequences of Violation

### Pre-Commit Hook

Commits with credentials will be **automatically blocked**:

```
Error: Potential secret detected in red-thread/src/main/resources/application.yml
Matched lines:
5:    password: mypassword

Secrets must not be committed. Use environment variables or .env files instead.
```

### Manual Override

Bypassing the hook is **strongly discouraged**:

```bash
git commit --no-verify  # ⚠️ DO NOT DO THIS
```

## Exceptions

**There are no exceptions to this policy.**
