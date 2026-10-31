$ErrorActionPreference = "Stop"
Push-Location $PSScriptRoot
try {
    New-Item -ItemType Directory -Force .build | Out-Null
    $sources = @(Get-ChildItem src/main/java, src/test/java -Filter *.java -Recurse | ForEach-Object { $_.FullName })
    & javac -encoding UTF-8 -d .build @sources
    if ($LASTEXITCODE -ne 0) { throw "Compilation failed" }
    & java -cp .build demo.ShopSelfTest
    if ($LASTEXITCODE -ne 0) { throw "Self-test failed" }
} finally { Pop-Location }
