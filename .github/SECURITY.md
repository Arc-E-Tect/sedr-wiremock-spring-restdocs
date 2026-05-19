# Security Policy

## Supported Versions

| Version | Supported |
| ------- | --------- |
| Latest  | ✅        |

## Reporting a Vulnerability

Please **do not** report security vulnerabilities through public GitHub issues.

Instead, use GitHub's private vulnerability reporting:
[Report a vulnerability](https://github.com/Arc-E-Tect/sedr-wiremock-spring-restdocs/security/advisories/new)

You can expect an initial response within **5 business days**.
Once the issue is confirmed, a patch will be released as soon as possible, and you will be credited in the release notes unless you prefer to remain anonymous.

## Security Practices

- Secrets are never stored in the repository. All credentials are managed via environment variables and GitHub Secrets.
- Pre-commit hooks enforce zero-credential policy on every commit. See [ZERO_CREDENTIALS_POLICY.md](../ZERO_CREDENTIALS_POLICY.md).
- GitHub Actions workflows are pinned to commit SHAs where possible to prevent supply-chain attacks.
- All changes to `main` require a pull request reviewed by the repository owner.
