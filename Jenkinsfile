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
                script {
                    // Явно указываем полное имя репозитория
                    def imageName = "vicryabenko/devops01:${env.BUILD_ID}" 
                    
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        // Собираем образ
                        def image = docker.build(imageName)
                        
                        // Добавляем тег latest
                        docker.image(imageName).tag('latest')
                        
                        // Пушим оба тега
                        image.push()
                        docker.image(imageName).push('latest')
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
