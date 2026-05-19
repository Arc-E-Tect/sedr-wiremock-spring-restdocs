# Git Hooks

This directory contains Git hooks that enforce quality standards and prevent common mistakes.

## Setup

Enable the hooks by running:

```bash
./scripts/setup-hooks.sh
```

This configures Git to use hooks from this directory instead of `.git/hooks`.

## Available Hooks

### pre-commit

Validates changes before they are committed:

1. **Keystore Detection**: Blocks commits containing keystore files (.p12, .pfx, .jks, .ks, .keystore, .truststore)
2. **Secret Detection**: Scans staged files for hardcoded credentials (passwords, API keys, tokens, etc.)
   - Allows placeholders: `password: <your-password>`, `password: ${PASSWORD}`
   - Allows environment variables and comments
   - See [ZERO_CREDENTIALS_POLICY.md](../ZERO_CREDENTIALS_POLICY.md) for details
3. **UTF-8 BOM Detection**: Prevents files with UTF-8 BOM markers from being committed
4. **Workflow Validation**: Runs `actionlint` on modified GitHub Actions workflows
5. **Package Lock Consistency**: Ensures package-lock.json matches package.json

### pre-push

Validates changes before they are pushed to remote:

1. **PR Status Check**: Prevents pushing to branches with merged or closed pull requests
   - Blocks pushes to branches with merged PRs (hard error)
   - Warns for closed PRs (allows continue with confirmation)
   - Skips check for: main, master, maintenance, section/chapter branches (sedr_sec##_ch##) and their hyphenated derivatives, worktree branches
   - Requires GitHub CLI (`gh`) to be installed and authenticated

2. **Semantic Release Validation**: Validates release.config.js changes
   - Runs `semantic-release --dry-run` when release configs are modified
   - Only validates changes being pushed to main branch
   - Requires GITHUB_TOKEN environment variable

## Bypassing Hooks

In rare cases where you need to bypass hooks:

```bash
# Skip pre-commit checks (NOT RECOMMENDED)
git commit --no-verify

# Skip pre-push checks (NOT RECOMMENDED)
git push --no-verify
```

**Warning**: Bypassing hooks can lead to:
- Security issues (committed secrets)
- Broken CI/CD pipelines (invalid workflows)
- Confusion (commits on merged PR branches)

Only bypass hooks if you understand the implications and have a valid reason.

## Dependencies

- **actionlint**: Required for workflow validation
  - Install: `brew install actionlint` (macOS)
  - Download: https://github.com/rhysd/actionlint

- **GitHub CLI (gh)**: Required for PR status checks
  - Install: `brew install gh` (macOS)
  - Authenticate: `gh auth login`

- **npm/npx**: Required for semantic-release validation
  - Should already be available as this project includes an Angular frontend

## Troubleshooting

### "actionlint: command not found"

Install actionlint via Homebrew:

```bash
brew install actionlint
```

### "gh: command not found"

Install the GitHub CLI:

```bash
brew install gh
gh auth login
```

### Hook not running

Verify hooks are configured:

```bash
git config core.hooksPath
# Should output: .githooks
```

If not, re-run setup:

```bash
./scripts/setup-hooks.sh
```
