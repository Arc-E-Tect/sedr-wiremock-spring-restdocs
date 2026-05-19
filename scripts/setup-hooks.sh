#!/usr/bin/env bash
set -euo pipefail

repo_root=$(git rev-parse --show-toplevel)
cd "$repo_root"

git config core.hooksPath .githooks
chmod +x .githooks/pre-commit .githooks/pre-push

echo "Git hooks installed from .githooks/"
echo ""
echo "Active hooks:"
echo "  pre-commit  — keystore detection, secret scanning, BOM check, actionlint"
echo "  pre-push    — PR status check, semantic-release validation"
