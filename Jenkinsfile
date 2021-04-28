pipeline {
  agent {
    node {
      label 'kaniko'
    }

  }
  stages {
    stage('Process Jobs') {
      steps {
        jobDsl(targets: ['*.groovy'].join('\n'), removedJobAction: 'IGNORE', removedViewAction: 'IGNORE', lookupStrategy: 'JENKINS_ROOT')
        sh '''cd ./jenkins_builder
echo \'{ "credsStore": "ecr-login" }\' > /kaniko/.docker/config.json
/kaniko/executor -f `pwd`/Dockerfile -c `pwd` --insecure --skip-tls-verify --cache=false --destination=${ECR_REPO}:orderbook-latest'''
      }
    }

  }
  environment {
    ECR_REPO = '108174090253.dkr.ecr.us-east-1.amazonaws.com/sre-course'
  }
}