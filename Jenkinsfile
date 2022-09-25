pipeline {

    agent {
        docker {
            image 'maven:3.5.0'
        }
    }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}