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

<div data-lift="SearchBlogPost">
	<div data-lift-id="datalist"></div>
	<input name="search"
		class="search-query" type="text"></input>
	<button type="submit" class="btn btn-small btn-primary" style="position: relative; top: -3px">
		<i class="icon-search icon-white"></i>
	</button>
</div>