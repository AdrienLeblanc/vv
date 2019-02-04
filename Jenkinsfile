pipeline {
    agent {
        docker {
            image 'maven:3.6.0-alpine'
            args '-v $HOME/.m2:/root/.m2'
        }
     }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: 'target/**/*.jar',
                                     fingerprint: true
                }
                failure {
                    mail to: 'erwan.iquel@gmail.com',
                         subject: "Failed pipeline: ${currentBuild.fullDisplayName}",
                         body: "Something is wrong with ${env.BUILD_URL}"
                }
            }
        }
    }
}