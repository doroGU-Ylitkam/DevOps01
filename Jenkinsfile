pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo '🧪 Running tests with Maven...'
                sh 'chmod +x mvnw'  // Добавляем права на выполнение
                sh './mvnw test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
    }
}
