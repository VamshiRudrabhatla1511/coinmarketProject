#!/usr/bin/env bash

function help_text() {
    echo "Runs the coin market app tests with given capabilities"
    echo "Usage: run_tests -e=<local|remote> -p=<mac|windows>"
    echo "-e, --env             Environment: local|remote"
    echo "-p, --platform        Platform name: Mac|Windows"
    echo "-n, --browser_name    Custom Browser"
    echo "-t, --tag             Tag name: Optional, to run a set of tests that have a unique tag name"
    echo "-b, --build-name      Custom Build name to be shown in remote server. Optional; Auto populated with repository details"
    echo "-h, --help            Help"
}

script_path="$(dirname $0)"

# Get command line arguments
for i in "$@"
do
case $i in
    -e=*|--env=*)
        env="${i#*=}"
        shift
        ;;
    -p=*|--platform=*)
        platform="${i#*=}"
        shift
        ;;
    -n=*|--browser_name=*)
        browser_name="${i#*=}"
        shift
        ;;
    -t=*|--tag=*)
        run_tag="@${i#*=}"
        shift
        ;;
    -b=*|--build-name=*)
        build_name="${i#*=}"
        shift
        ;;
     -ae=*|--app=*)
                app="${i#*=}"
                shift
                ;;
    -h*|--help*)
        help_text;
        exit 0
        ;;
    --default)
    shift
    ;;
    *)
    # unknown option
    echo "Unknown option: '$i'"
    exit -1
    ;;
esac
done

#If parameter does NOT exist with set Environment Variable
if [ -z "$platform" ]; then
    echo "${platform}"
fi

#Check env
if [ "$env" != "local" -a "$env" != "remote" ]; then
    echo "Invalid test environment"
    exit -1
fi

#Check application env
if [ "$app" != "dev" -a "$app" != "qa"  -a "$app" != "prod" -a "$app" != "local" ]; then
    echo "Invalid application environment type. Cannot get the Site URL for the value specified-   ${app}"
    exit -1
    elif [ "$app" == "dev" ]; then
        echo "loading site URL and auth details of dev"
        site_url="https://coinmarketcap.com/"
    elif [ "$app" == "local" ]; then
    echo "loading site URL and auth details of local"

fi

#Check platform
if [ -z "$run_tag" ]; then
    run_tag=""
    if [[ -z "$platform"  &&  -z "$browser_name" ]]; then
        echo "Invalid platform"
        exit -1
    else
        run_tag="@sanity"
    fi
fi

echo "Running Tests with the Cucumber Options Tag : ${run_tag}"

if [ "$env" == "local" ]; then
    mvn test -q -P runAllTests -Dtest.env="${env}" -Dtoken.generator.url="${token_generator_url}" -Dbrowser.name="${browser_name}" -Dsite.url=${site_url} -Dmaster.template.url="${master_template_url}" -Dauth.username=${auth_username} -Dauth.password=${auth_pwd} -Dbase.url=${base_url} "-Dcucumber.options=--tags ${run_tag} --tags ~@ignore"
fi