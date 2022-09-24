pipeline {

    agent {
        docker { image 'maven:3.8.1-adoptopenjdk-11' }
    }
    stages {
        stage('build') {
            steps {
                sh 'mvn build'
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}