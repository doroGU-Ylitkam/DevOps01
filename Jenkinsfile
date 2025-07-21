pipeline {
    agent any

    environment {
        DOCKER_HUB_REPO = 'vicryabenko/devops01'
        APP_PORT = '8081' 
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
        stage('Pull') {
            steps {
                echo '🚀 Deploying Docker container...'
                script {
                    // Останавливаем и удаляем старый контейнер (если есть)
                    sh '''
                        docker stop my-app || true
                        docker rm my-app || true
                    '''
                    // Запускаем новый контейнер из образа
                    sh """
                        docker run -d \
                            --name my-app \
                            -p ${env.APP_PORT}:${env.APP_PORT} \
                            ${env.DOCKER_HUB_REPO}:${env.BUILD_ID}
                    """
                }
            }
        }
    }

    post {
        success {
            echo "✅ Success! Container is running: http://<SERVER_IP>:${env.APP_PORT}"
        }
    }
}
