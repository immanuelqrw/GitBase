from pathlib import Path
from typing import (
    Dict,
    List
)

from github import (
    Github,
    Issue,
    Milestone,
    Project
)
import yaml

# TODO Integrate Label API

GIT_TOKEN_FILE: Path = Path("./git-token.yaml").absolute

def get_git_token(token_file: Path) -> str:
    with GIT_TOKEN_FILE.open(mode="r", encoding="utf-8") as gtf:
        git_data: Dict[str, str] = yaml.safe_load(gtf)
        GIT_TOKEN: str = git_data["token"]

# TODO Create milestone list
# TODO Create issue map

def instantiate_repository(github_api: Github, owner_name: str, repository_name: str) -> Repository:
    """Create README, Select .gitignore, select LICENSE"""
    # TODO Integrate Repository API
    # TODO Create Repository

    # TODO Integrate License API
    # TODO Integrate Gitignore API
    # TODO Create .gitingore
    # TODO Select license
    pass

def create_default_milestones(repository: Repository) -> List[Milestone]:
    return [
        repository.create_milestone(title=milestone)
        for milestone in milestones
    ]

def create_default_project_board(repository: Repository) -> Project:
    pass

def create_default_issues(repository: Repository) -> List[Issue]:
    # TODO Get map of all labels
    labels_map: Dict[str, List[Label]] = {}
    labels: List[Label] = []

    # TODO Get map of all milestones
    milestone_map: Dict[str, Milestone] = {}

    # TODO Create Map of default issues, descriptions, and labels
    return [
        repository.create_issue(title=issue, labels=labels_map[issue], milestone[issue])
        for issue in issues
    ]

def replace_milestone(repository: Repository) -> Milestone:
    pass


if __name__ == "__main__":
    git_token: str = get_git_token(GIT_TOKEN_FILE)
    github_api: Github = Github(git_token)

    # TODO Get owner name of repository
    # TODO Get repository name from user
    # owner: str = ""
    # repository_name: str = ""
    # repository: Repository = github_api.get_repo(f"{owner}/{repository_name}")

    repository: Repository = instantiate_repository(github_api, owner_name, repository_name)
    milestones: List[Milestone] = create_default_milestones(repository)
    issues: List[Issue] = create_default_issues(repository)
