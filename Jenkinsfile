pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo '🧪 Running tests with Maven...'
                sh './mvnw test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'  // Публикация результатов тестов
                }
            }
        }
    }
}
