pipeline {
  agent {
    node {
      label 'kaniko'
    }

  }
  stages {
    stage('Process Jobs') {
      steps {
        sh '''cd ./compose
echo \'{ "credsStore": "ecr-login" }\' > /kaniko/.docker/config.json
/kaniko/executor -f `pwd`/Dockerfile.db -c `pwd` --insecure --skip-tls-verify --cache=false --destination=${ECR_REPO}:orderbookdb-latest
/kaniko/executor -f `pwd`/Dockerfile.api -c `pwd` --insecure --skip-tls-verify --cache=false --destination=${ECR_REPO}:orderbookapi-latest'''
      }
    }

  }
  environment {
    ECR_REPO = '108174090253.dkr.ecr.us-east-1.amazonaws.com/sre-course'
  }
}
