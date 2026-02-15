#!/usr/bin/env sh
set -e
JAVA_HOME_DEFAULT="$HOME/.local/share/mise/installs/java/17.0.2"
if [ -d "$JAVA_HOME_DEFAULT" ]; then
  export JAVA_HOME="$JAVA_HOME_DEFAULT"
  export PATH="$JAVA_HOME/bin:$PATH"
fi
exec gradle "$@"
