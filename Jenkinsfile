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
                pushToCloudFoundry cloudSpace: 'smeaney-cnt', credentialsId: 'PivotalWebServices', organization: 'solstice-org', target: 'api.run.pivotal.io'
            }
        }
    }
    post{
        success{
            cleanWs()
        }
    }
}