# Software Engineering Done Right - Source Code

## Introduction

Repository with the sources for the book _Software Engineering done Right_

The book uses an example application to illustrate how to apply good practices to develop excellent software. The application is called _IFF - Identification: Friend or Family_.
The source code for IFF is located in the directory `red-thread`.

Every chapter from the book is accompanied by its own branch of this repository. 
The names of the branches are directly linked with the chapter of the book. 
For example, the first chapter in the book in which the IFF project is created is chapter 2 of section 4. 
The branch for this chapter is therefore called `sedr_sec04_ch02` (**S**oftware **E**ngineering **d**one **R**ight, **sec**tion **04** **ch**apter **02**).

Throughout the book you will find additional examples to show specific aspects of software engineering done right.
Each of these examples will have its own branch as well, with the same naming convention applied.

Note: over time, the libraries the example application IFF depends on will change.
New versions will become available, or existing libraries will be replaced by new libraries that are equivalent. These changes of the dependencies will be applied to all branches, including the main branch.
This will prevent the introduction of vulnerabilities, for example, by outdated dependencies.

To learn more about the individual examples used to illustrate the practices around the software engineering done right, see the relevant README.md files in each directory.

## Dependency maintenance

The legacy automation scripts that used to live in this repository (`updateDependencies.ps1`, `updateNpmPackages.ps1`, etc.) have been removed. Maintainers should now run the shared automation that lives in the Tools project instead:

👉 https://github.com/Arc-E-Tect/SoftwareEngineeringDoneRight-Tools

Clone that repository and follow its README to execute the update workflows (Gradle refresh, npm updates, sanitizer, PR generation, …). Keeping the tooling in a single project makes it easier to evolve and reuse across all SoftwareEngineeringDoneRight repositories.

## Changelog and Release Management

### GitHub Release Announcements

All releases are automatically published to GitHub with full changelog details included in the release notes. This is handled by the `@semantic-release/github` plugin configured in all `release.config.js` files. When a new version is released:

1. A GitHub release is created with the version number as the title
2. The release notes include all commit messages formatted according to the changelog configuration
3. Commits are grouped by type (✨ Features, 🐛 Fixes, etc.) with emoji icons
4. Issue references (#123) and user mentions (@user) are automatically linked

You can view all releases at: https://github.com/Arc-E-Tect/SoftwareEngineeringDoneRight-Code/releases

### Commit Message Format

This repository follows the [Conventional Commits](https://www.conventionalcommits.org/) specification. Each commit message should be structured as:

```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

**Supported types:**
- `feat`: New features (triggers minor version bump)
- `fix`: Bug fixes (triggers patch version bump)
- `refactor`: Code refactoring (triggers patch version bump)
- `improvement`: Improvements to existing features (triggers minor version bump)
- `scenario`: Test scenarios (triggers patch version bump)
- `maintenance`: Maintenance tasks (triggers patch version bump)
- `docs`: Documentation changes (triggers patch version bump)
- `test`: Test additions or modifications (triggers patch version bump)
- `build`: Build system changes (triggers patch version bump)
- `ci`: CI/CD configuration changes (triggers patch version bump)
- `chore`: Miscellaneous changes (triggers patch version bump)

**Breaking changes:** Add `!` after type or `BREAKING CHANGE:` in footer to trigger major version bump.

Example:
```
feat(authentication)!: migrate to OAuth 2.0

BREAKING CHANGE: Legacy session-based authentication is no longer supported.
All clients must update to use OAuth 2.0 tokens.
```

Enjoy...

Arc-E-Tect
