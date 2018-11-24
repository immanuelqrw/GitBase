GitBase
-------

Git Project instantiation scripts

To Start
- Add ~/.github credentials file for access
  - Place token in file
- COPY config/repository-init.yaml.example to config/repository-init.yaml
  - Fill with correct values for repository
- COPY config/script-action.yaml.example to config/script-action.yaml
  - Fill with correct values for actions to take
- Execute script in scripts directory named `generate-alias-config.py`
  - This is intended to create machine readable version of configuration file
