"""Converting YAML with anchors into standardized configuration file"""
from pathlib import Path
import yaml


# TODO Fix paths
ROOT_DIR: Path = Path(".").absolute().parent
CONFIG_DIR: Path = ROOT_DIR.joinpath("src/main/resources")

# TODO Rename old config file to .alias
OLD_CONFIG_FILE: Path = CONFIG_DIR.joinpath("git-base-config.yaml.alias")
NEW_CONFIG_FILE: Path = CONFIG_DIR.joinpath("git-base-config.yaml")


if __name__ == "__main__":
    with OLD_CONFIG_FILE.open(mode="r") as ocf, NEW_CONFIG_FILE.open("w") as ncf:
        old_config = yaml.safe_load(ocf)

        # Converting to string to prevent re-implementing aliases
        yaml.safe_dump(old_config, ncf, default_flow_style=False, indent=2)
