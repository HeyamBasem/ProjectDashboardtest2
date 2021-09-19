FROM openjdk:16
RUN mkdir -p /webapp
COPY ./target/ProjectDashboardtest2-1.0-SNAPSHOT.war /webapp
WORKDIR /webapp
RUN mv *.war webapp.war
ENTRYPOINT ["java","-war","webapp.war"]
#4c0f0b8a84d673080b6399126d05189301fdc83c522e57dba7421c3506cee65a
