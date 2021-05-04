pipeline {
  agent {
    node {
      label 'kaniko'
    }

  }
  stages {
    stage('Build DB') {
      steps {
        container(name: 'kaniko') {
          sh '''echo \'{ "credsStore": "ecr-login" }\' > /kaniko/.docker/config.json
/kaniko/executor -f `pwd`/compose/Dockerfile.db -c `pwd` --insecure --skip-tls-verify --cache=false --destination=${ECR_REPO}:orderbookdb-latest'''
        }

      }
    }
    stage('Build API') {
      steps {
        container(name: 'kaniko') {
          sh '''echo \'{ "credsStore": "ecr-login" }\' > /kaniko/.docker/config.json
/kaniko/executor -f `pwd`/compose/Dockerfile.api -c `pwd` --insecure --skip-tls-verify --cache=false --destination=${ECR_REPO}:orderbookapi-latest'''
        }
      }
    }

  }
  environment {
    ECR_REPO = '108174090253.dkr.ecr.us-east-1.amazonaws.com/sre-course'
  }
}
