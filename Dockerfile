#Dockerfile
#FROM openjdk:21-ea-24-oracle
FROM openjdk:22-ea-jdk

# directorio de trabajo
WORKDIR /app
# jar
COPY target/bdget-0.0.1-SNAPSHOT.jar app.jar
# wallet
COPY Wallet_QSYVMD9J8GOANRS4 /app/oracle_wallet/
EXPOSE 8082
