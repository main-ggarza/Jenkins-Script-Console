/////////////////////////////////////////
///
///  This will search for all the builds of jobName
///  and check which ones are older than daysOld
///
///  Description:
///  Searches for all builds of a specified job and
///  deletes the ones that are older than a specified
///  number of days.
///
/////////////////////////////////////////

def jobName = 'Sandbox/test_gg'
def currentTime = System.currentTimeMillis()
def job = Jenkins.instance.getItemByFullName(jobName)
def daysOld = .5
def millisPerDay = 1000 * 60 * 60 * 24

if (job != null) {
    def builds = job.getBuilds()

    builds.each { build ->
        def buildTime = build.getTimeInMillis()
        def buildInDays = (currentTime - buildTime) / millisPerDay

        if (buildInDays > daysOld) {
            println "Build #${build.number} is ${buildInDays} days old. Deleting..."

            build.delete()

            println "Build #${build.number} has been deleted."
        }
    }

    println "All builds older than ${daysOld} days have been processed. Job Completed."
} else {
    println "Job '${jobName}' does not exist. Please check the job name."
}
