pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo '🧪 Running tests with Maven...'
                sh 'chmod +x mvnw'
                sh './mvnw test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Build') {
            steps {
                echo '🔨 Building JAR artifact...'
                sh './mvnw clean package -DskipTests'  // Сборка без повторного запуска тестов
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'  // Сохранение артефакта в Jenkins
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // Явно указываем путь к Dockerfile и контекст сборки
                    dockerImage = docker.build("my-app:${env.BUILD_NUMBER}", ".")
                }
            }
        }
    }
}
