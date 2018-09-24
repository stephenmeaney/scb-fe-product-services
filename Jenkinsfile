pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'gradle check'
                junit 'build/test-results/**/*.xml'
            }
        }
        stage('Build'){
            steps{
                sh 'gradle clean assemble'
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