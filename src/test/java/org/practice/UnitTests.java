package org.practice;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@SelectPackages("org.practice")
@IncludeTags("UnitTest")
@Suite
public class UnitTests {
}
