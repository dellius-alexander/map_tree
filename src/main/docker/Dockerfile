FROM openjdk:11.0.12-jdk
MAINTAINER hyfi
RUN mkdir -p /usr/src/${appname}
COPY maven /usr/src/${appname}
VOLUME ["/usr/src/${appname}/data"]
WORKDIR /usr/src/${appname}
CMD ["java","-cp","${project.build.finalName}.${project.packaging}:lib/*","com.hyfi.map_tree.Map_Tree"]
