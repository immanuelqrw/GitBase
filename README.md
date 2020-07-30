# GitBase

![GitHub](https://img.shields.io/github/license/immanuelqrw/GitBase)
![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/immanuelqrw/GitBase)
![GitHub top language](https://img.shields.io/github/languages/top/immanuelqrw/GitBase)

![GitHub issues](https://img.shields.io/github/issues-raw/immanuelqrw/GitBase)
![GitHub pull requests](https://img.shields.io/github/issues-pr-raw/immanuelqrw/GitBase)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=immanuelqrw_GitBase&metric=alert_status)](https://sonarcloud.io/dashboard?id=immanuelqrw_GitBase)

---

Git Project instantiation scripts

## Start
- Add ~/.github credentials file for access
  - Place token in file
- `COPY` config/repository-init.yaml.example to config/repository-init.yaml
  - Fill with correct values for repository
- `COPY` config/script-action.yaml.example to config/script-action.yaml
  - Fill with correct values for actions to take
- Execute script in scripts directory named `generate-alias-config.py`
  - This is intended to create a machine-readable version of configuration file
