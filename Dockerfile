from uno
expose 8080
workdir /app
cmd ["/usr/", "-jar". "AgendaBd-0.0.1-SNAPSHOT.jar"]
add /temporalAgenda/AgendaBd-0.0.1-SNAPSHOT.jar /app/