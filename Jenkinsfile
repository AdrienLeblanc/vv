pipeline {
    agent {
        docker {
            image 'maven:3.6.0-alpine'
            args '-v $HOME/.m2:/root/.m2'
        }
     }
     environment {
                     SONAR_CLOUD_LOGIN_TOKEN = credentials('sonarcloud-login')
                 }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
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
        stage('Sonar') {
            steps {
                sh 'mvn verify -P sonar -DskipTests -Dsonar.login=$SONAR_CLOUD_LOGIN_TOKEN -Dsonar.branch.name=$GIT_LOCAL_BRANCH'
            }
        }
    }
}