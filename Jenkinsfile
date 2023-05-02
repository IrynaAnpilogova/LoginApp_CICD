pipeline {
    agent any

    stages {
        stage('Build Docker images') {
            steps {
                sh '''cd LoginApp &&
                docker build -t app-image . &&
                cd ../LoginAppTests &&
                docker build -t tests-image . &&
                docker pull seleniarm/standalone-chromium
                '''
            }
        }
        stage('Start containers') {
            steps {
                sh 'docker stop my-app my-selenium || true'
                sh '''
                docker run -d --rm -p 8081:80 --name my-app app-image &&
                docker run -d --rm -p 4444:4444 --link my-app:my-app --name my-selenium seleniarm/standalone-chromium
                '''
            }
        }
        stage('Run tests') {
            steps {
                sh '''
                docker run --link my-selenium:my-selenium -v ${PWD}/LoginAppTests:/data tests-image mvn clean test
                '''
                // Archive test report
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }
    }
    post {
        cleanup {
            sh 'docker stop my-app my-selenium'
        }
    }
}