FROM adoptopenjdk/openjdk12:alpine-slim AS overlay

RUN mkdir -p jwt-overlay
COPY ./src jwt-overlay/src/
COPY ./comm jwt-overlay/comm/
COPY ./data jwt-overlay/data/
COPY ./client jwt-overlay/client/
COPY ./business jwt-overlay/business/
COPY ./web jwt-overlay/web/
COPY ./gradle/ jwt-overlay/gradle/
COPY ./gradlew ./settings.gradle ./build.gradle ./gradle.properties /jwt-overlay/

RUN mkdir -p ~/.gradle \
    && echo "org.gradle.daemon=false" >> ~/.gradle/gradle.properties \
    && echo "org.gradle.configureondemand=true" >> ~/.gradle/gradle.properties \
    && cd jwt-overlay \
    && chmod 750 ./gradlew \
    && ./gradlew --version;

RUN cd jwt-overlay \
    && ./gradlew clean build --parallel;

FROM adoptopenjdk/openjdk12:alpine-jre AS jwt

LABEL "Description"="JWT Service"

RUN cd / \
    && mkdir -p /etc/jwt/db \
    && mkdir -p /var/jwt/log \
    && mkdir -p /var/jwt/pid \
    && mkdir -p jwt-overlay;

COPY --from=overlay jwt-overlay/build/libs/jwt.war jwt-overlay/

EXPOSE 8100

ENV PATH $PATH:$JAVA_HOME/bin:.

WORKDIR jwt-overlay
ENTRYPOINT ["java", "-server", "-noverify", "-Xmx2048M", "-Dspring.profiles.active=production", "-jar", "jwt.war"]
