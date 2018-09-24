pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh './gradlew check'
                junit 'build/test-results/**/*.xml'
            }
        }
        stage('Build'){
            steps{
                sh './gradlew clean assemble'
            }
        }
        stage('Deploy'){
            steps{
                sh 'cf push'
            }
        }
    }
    post{
        success{
            cleanWs()
        }
    }
}