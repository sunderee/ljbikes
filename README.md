# LJBikes

Native Android app that uses [JCDecaux APIs](https://developer.jcdecaux.com/#/opendata/vls?page=getstarted) in order to present the same information as (official) [BicikeLJ](https://www.bicikelj.si) application. It does not allow for releasing bikes from their stands.

## Usage

**Work in progress.** This is a native Android project. In order for the project to be useful, one has to provide the following credentials:

```bash
BASE_URL_FR=XXX
BASE_URL=XXX
API_KEY=XXX
API_TOKEN_CODE=XXX
API_TOKEN_KEY=XXX
MAPS_API_KEY=XXX
```

## Functionality

![First recording](./.github/ljbikes_01.gif)

Application makes an initial API request to obtain information about stands (name, address, geographic location, ...) and displays them on the map. Because Ljubljana is a small city, and there's 50+ stands, we need to implement clustering.

![Second recording](./.github/ljbikes_02.gif)

Click on a station marker fires a second API request which fetches list of bikes at the given station. This information includes bike number, number of the stand where bike can be found, ratings, etc.

![Third recording](./.github/ljbikes_03.gif)

Lastly, the application offers searching for stations by their name or address. From there, user can once again obtain station details and bikes it hosts.

## License

Project is open-sourced under [MIT license](./LICENSE).