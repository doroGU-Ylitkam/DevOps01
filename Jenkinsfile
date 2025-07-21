pipeline {
    agent any

    environment {
        DOCKER_HUB_REPO = 'vicryabenko/devops01'
    }
    
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
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        stage('Docker Build & Push') {
            steps {
                echo '🐳 Building and pushing Docker image...'
                script {
                    // Логин в Docker Hub
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        // Сборка и тегирование
                        def image = docker.build("${env.DOCKER_HUB_REPO}:${env.BUILD_ID}")
                        // Пуш образа
                        image.push()
                        // Дополнительно: пуш с тегом 'latest'
                        image.push('latest')
                    }
                }
            }
        }
    }

    post {
        success {
            echo "✅ Success! Image pushed to Docker Hub: ${env.DOCKER_HUB_REPO}:${env.BUILD_ID}"
        }
        failure {
            echo "❌ Pipeline failed. Check logs for details."
        }
    }
}
