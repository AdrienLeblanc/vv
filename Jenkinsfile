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
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Sonar') {
            environment {
                SONAR_CLOUD_LOGIN_TOKEN = credentials('sonarcloud-login')
            }
            steps {
                sh 'mvn verify -P sonar -Dsonar.login=$SONAR_CLOUD_LOGIN_TOKEN'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: '**/target/**/*.jar',
                                     fingerprint: true
                }
            }
        }
    }
}