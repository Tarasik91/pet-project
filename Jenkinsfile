pipeline {

    agent {
        docker {
            image 'maven:3.5.0'
        }
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