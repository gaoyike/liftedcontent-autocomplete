This is an autocomplete widget which can be used in Lift webapps. It uses HTML5 functionality so it is only working on some browsers (Firefox and Opera at the moment).
To try it out follow the steps below.

# Check out the code

  $ git clone git://github.com/sbradl/AutoComplete.git

# Run SBT

  $ sbt

# Compile and publish

  $ sbt> compile

  $ sbt> publish-local

# Add dependecy to your project

"eu.sbradl" %% "autocomplete" % "1.0.0" % "compile"

# Example usage

## HTML

    <span data-lift-id="datalist"></span>
    <input name="search" type="text"></input>