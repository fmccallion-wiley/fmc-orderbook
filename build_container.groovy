tages {
  stage('Build DB Image') {
    when {
      anyOf{
        changeset "compose/Dockerfile.db"
        triggeredBy cause: "UserIdCause"
      }
    }
    steps {
      container('kaniko') {
        sh '''echo '{ "credsStore": "ecr-login" }' > /kaniko/.docker/config.json
        /kaniko/executor -f `pwd`/compose/Dockerfile.db -c `pwd` --insecure --skip-tls-verify --cache=false --destination=${ECR_REPO}:cddb-latest'''
      }
    }
  }
  stage('Build App Image') {
    when {
      anyOf{
        changeset "compose/Dockerfile.api"
        triggeredBy cause: "UserIdCause"
      }
    }
    steps {
      container('kaniko') {
        sh '''echo '{ "credsStore": "ecr-login" }' > /kaniko/.docker/config.json
        /kaniko/executor -f `pwd`/compose/Dockerfile.api -c `pwd` --insecure --skip-tls-verify --cache=false --destination=${ECR_REPO}:cdapi-latest'''
      }
    }
  }
}
