def buildStage() {
    stage('Build stage'){
        withEnv(["PATH=/usr/local/bin:$PATH"]) {
            dir(serviceDir) {
                if(fileExists(".git")) {
                    echo "✅ Repo exists, pulling latest changes"
                    sh "git reset --hard && git clean -fd"
                    sh "git pull origin main"
                }
                else {
                    echo "🔹Repo didnt exist and will be clone"
                    sh "git clone https://github.com/HappilyStreet/Prometheus-jenkins.git ."
                }
            }
        }
    }
    echo "✅ Build complete"
}
return this