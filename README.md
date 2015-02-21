# Postfix Attachment Filter
Filters email messages that contain executables in their attachments.

Executables are searched by extension and content.

## Archives

The filter also support the following types of archives:
- zip
- tar
- arj
- 7zip
- gzip
- bzip2
- xz
- z
- lzma
- Pack200

Password protected archives are not supported.

## Installation

Please attach the filter as described in http://www.postfix.org/FILTER_README.html

## Depencencies

- [JavaMail](http://www.oracle.com/technetwork/java/javamail/index.html)
- [Commons IO](http://commons.apache.org/proper/commons-io/)
- [Commons Compress](http://commons.apache.org/proper/commons-compress/project-summary.html)
- [XZ for Java](http://tukaani.org/xz/java.html)

## License

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.