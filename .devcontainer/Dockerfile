# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.194.0/containers/java/.devcontainer/base.Dockerfile

## Start of Dockerfile
ARG VARIANT=""
FROM mcr.microsoft.com/vscode/devcontainers/java:0-${VARIANT}
#####################################################################
#######################  CLI BUILD ARGUMENTS  #######################
# [Option] Install zsh
ARG INSTALL_ZSH=""
# Install needed packages and setup non-root user. Use a separate RUN statement to add your own dependencies.
ARG USERNAME=""
ARG USER_UID=""
ARG USER_GID=$USER_UID
# GIT User Info
ARG GIT_USER=""
ARG GIT_EMAIL=""
# [Option] Install Maven
ARG INSTALL_MAVEN=""
ARG MAVEN_VERSION=""
# [Option] Install Gradle
ARG INSTALL_GRADLE=""
ARG GRADLE_VERSION=""
# [Choice] Node.js version: none, lts/*, 16, 14, 12, 10
ARG NODE_VERSION=""
# Workspace/App directory
ARG APP_DIR=""
# Git repo
ARG GIT_REPO=""
# Git Branch
ARG GIT_BRANCH=""
# User Passwd
ARG USERCRED=""
#####################################################################
ENV APP_DIR=${APP_DIR}
WORKDIR ${APP_DIR}
#####################################################################
RUN rm -rf ~/.oh-my-zsh && sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)" "Y"
# USER root
# Configure apt and install packages
RUN apt-get update \
    && apt-get -y install --no-install-recommends apt-utils 2>&1 \
    #
    # Verify git, process tools, lsb-release (common in install instructions for CLIs) installed
    && apt-get -y install git procps lsb-release 
    # #
    # # Create a non-root user to use if preferred - see https://aka.ms/vscode-remote/containers/non-root-user.
    # && groupadd --gid $USER_GID $USERNAME \
    # && useradd -s /bin/bash --uid $USER_UID --gid $USER_GID -m $USERNAME \
    # # Clean up
    # && apt-get autoremove -y \
    # && apt-get clean -y 
    # && rm -rf /var/lib/apt/lists/*
RUN  git config --global user.email "${GIT_EMAIL}" \
&& git config --global user.name "${GIT_USER}"
RUN apt-get update && apt-get install -y sudo

RUN if [ "${INSTALL_MAVEN}" == "true" ]; then su ${USERNAME} -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven \"${MAVEN_VERSION}\""; fi 
RUN if [ "${INSTALL_GRADLE}" == "true" ]; then su ${USERNAME} -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install gradle \"${GRADLE_VERSION}\""; fi
RUN if [ "${NODE_VERSION}" != "none" ]; then su ${USERNAME} -c "umask 0002 && . /usr/local/share/nvm/nvm.sh && nvm install ${NODE_VERSION} 2>&1"; fi

RUN mkdir -p ${APP_DIR} 
# RUN sudo chown -R ${USERNAME}:${USERNAME} ${APP_DIR}
# [Optional] Uncomment this section to install additional OS packages.
# RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
# && apt-get -y install --no-install-recommends git
# [Optional] Uncomment this line to install global node packages.
# RUN su ${USERNAME} -c "source /usr/local/share/nvm/nvm.sh && npm install -g <your-package-here>" 2>&1]
# RUN git clone ${GIT_REPO} ${APP_DIR} \
# && cd ${APP_DIR}

RUN cd ${APP_DIR}
RUN  git config --global user.email "${GIT_EMAIL}" \
&& git config --global user.name "${GIT_USER}"
# RUN chown -R ${USERNAME}:${USERNAME} ${APP_DIR}
# USER ${USERNAME}
# Enable docker in docker
COPY library-scripts/*.sh /tmp/library-scripts/
# RUN chown -R ${USERNAME}:${USERNAME} /tmp/library-scripts/
ENV DOCKER_BUILDKIT=1
RUN apt-get update && /bin/bash /tmp/library-scripts/docker-in-docker-debian.sh "true" \
&& rm -rf /var/lib/apt/lists/*
# RUN echo "${USERNAME}:${USERCRED}" | chpasswd
RUN chsh --shell /usr/bin/zsh
# RUN usermod -aG docker ${USERNAME} && newgrp docker
ENTRYPOINT ["/usr/local/share/docker-init.sh"]
VOLUME [ "/var/lib/docker" ]
CMD ["sleep", "infinity"]