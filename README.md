# Pulse

Pulse is an opensource modern Android application that helps monitor server status and notifies
users when
something goes wrong. Users can create requests specifying the type (GET, POST, etc.) and URL. The
system periodically checks the server status and sends a notification if the response code is 400 or
higher.

## Installation

### There are several ways to get release .apk of Pulse application:

##### 1. Download package from [GitHub Releases](https://github.com/T-e-i-l-s/Pulse/releases)

This is the fastest way. In [Releases](https://github.com/T-e-i-l-s/Pulse/releases) you can find all
release versions of Pulse.

##### 2. Downloading package from last CI Job

This approach is more flexible than the first one because you can get both release and debug
versions. Also you can download package from develop and other branches if you want to get some
specific experience. **We recommend to use last version from main branch because it's more
stable.**

##### 3. Build project by your own

The hardest way which provides you full control of the build process. To do this clone repository
and run build process from Android Studio.

## Development & Testing

Please see our development docs for full details regarding work on the Pulse source code.

If you are looking to add some new functionality we would be fun to provide you any help.

### Short project info

- Min SDK = 26 (Android 8)
- Jetpack Compose
- MVVM Architecture

## Licence

Pulse application is provided
under [MIT Licence](https://github.com/T-e-i-l-s/Pulse/blob/main/LICENSE).