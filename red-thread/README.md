# Software Engineering Done Right - Source Code

## Red Thread - IFF - Identification: Friend or Family

### Building the application

#### Prerequisites

To run build and run the application, it is required to have [Java 21](https://jdk.java.net/21/) installed.
The build is tested with [Amazon Corretto 21](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html).

It is required that you have Docker installed on your machine. The application uses a Docker container to run the WireMock server.

#### Build commands

To build the application and run all tests, execute from the commandline: 
```console
./gradlew build
```

To perform a clean build, execute from the commandline: 
```console
./gradlew clean build
```
To run the application, execute from the commandline:
```console
./gradlew bootRun
```

Verify that the application is running by navigating to [http://localhost:9090](http://localhost:9090) in a web browser.

To update all dependencies, execute from the commandline:
```console
./gradlew refreshVersions
```
After running the command, uncomment the lines with the latest version numbers in the files `versions.properties` and `gradle/lib.versions.toml`

After adding new dependencies, execute from the commandline:
```console
./gradlew refreshVersionsMigrate --mode=VersionsPropertiesAndPlaceholdersInCatalog
```
Followed by the commandline:
```console
./gradlew refreshVersions
```
After running the command, uncomment the lines with the latest version numbers in the files `versions.properties` and `gradle/lib.versions.toml`

To scan all dependencies for known vulnerabilities, ensure that your NVD API key is made available through the environment variable `NVD_APIKEY_SEDR`. The API key can be requested from NIST through [Request an API key](https://nvd.nist.gov/developers/request-an-api-key).

execute the commandline:
```console
./gradlew dependencyCheckAnalyze
```

### Testing

Each microservice runs on its own dedicated HTTPS port:

| Microservice   | Application port | docGen server | docGen WireMock |
|----------------|:----------------:|:-------------:|:---------------:|
| system-admin   | 8443             | 9090          | 9091            |
| user-account   | 8444             | 9580          | 9581            |
| auth-server    | 8445             | 9591          | 9582            |
| relationship   | 8446             | 9592          | 9583            |
| social-network | 8447             | 9593          | 9584            |

#### Source directory layout

Every microservice subproject (`system-admin`, `user-account`, `auth-server`, `relationship`, `social-network`) shares the same source directory structure.

| Source directory | Gradle suite / task | Role | Infrastructure required |
|---|---|---|---|
| `src/test` | `test` | JUnit 5 unit tests; all dependencies mocked with Mockito; 100% JaCoCo branch/line coverage required | none — pure JVM |
| `src/docGen` | `docGen` | Spring REST Docs test driver that starts the embedded app and records request/response snippets to `build/generated-snippets/` | embedded app + WireMock (internal) |

#### Task quick reference

| Task | Gradle suite | Source directory | In `build` |
|---|---|---|:---:|
| `test` | `test` | `src/test` | ✅ |
| `docGen` | `docGen` | `src/docGen` | — |

#### Verification commands

Standard build (unit tests, JaCoCo — no Docker required):

```console
./gradlew clean build --no-daemon --no-build-cache
```

Generate WireMock stubs and SDK documentation (Docker required for Testcontainers):

```console
./gradlew :system-admin:docGen :user-account:docGen :auth-server:docGen :relationship:docGen :social-network:docGen --no-configuration-cache
```

### Generation OpenAPI document

It is required to have **Node.js** and **PowerShell Core (`pwsh`)** installed on your machine.
PowerShell Core is available on Windows, macOS, and Linux from https://github.com/PowerShell/PowerShell.

The OpenAPI generation and distribution is handled by a single script:

```console
./gen-openapi.ps1
```

To inject a specific semantic version into the OpenAPI document, pass the `-Version` parameter:

```console
./gen-openapi.ps1 -Version 2.3.1
```

The script performs the following steps automatically:

1. **Pre-process fragments** — runs `prep-openapi` from `sedr_utils/openapi/prep_openapi` to merge the OpenAPI fragments from `usable-suspects/` into a single merged YAML file.
2. **Inject version** (optional) — replaces the `version:` field in the merged file with the value supplied via `-Version`.
3. **Bundle** — runs `@redocly/cli bundle` to produce a fully self-contained `iff_openapi.yaml` in a temporary location.
4. **Distribute** — calls `sedr_utils/openapi/distribute_openapi.ps1` to copy the bundled file to every microservice subproject discovered in `settings.gradle` (i.e. `<subproject>/src/main/resources/iff_openapi.yaml`).

#### Prerequisites

`prep-openapi` is invoked via `npx` — no global install needed. If you prefer a global install:

```console
cd ../sedr_utils/openapi/prep_openapi
npm install -g
```

On macOS/Linux, you may need to make it executable:

```console
chmod +x <path to>/prep_openapi
```

`@redocly/cli` is also invoked via `npx` — no global install needed. To install or update globally:

```console
npm install -g @redocly/cli@latest
```

#### Generating HTML documentation

To generate an HTML preview of the API documentation independently:

```console
npx prep-openapi -d ../usable-suspects iff_openapi_structure.yaml
npx --yes @redocly/cli build-docs ../usable-suspects/app/src/docs/api/merged_iff_openapi_structure.yaml --output ./app/build/docs/api/iff_openapi.html
```