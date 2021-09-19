Jenkinsfile (Declarative Pipeline)
pipeline {
    agent { docker { image 'webappimage' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}