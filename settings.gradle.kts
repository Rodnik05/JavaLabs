rootProject.name = "JavaLabs"
include("CatApp")
include("CatApp:DAO")
findProject(":CatApp:DAO")?.name = "DAO"
