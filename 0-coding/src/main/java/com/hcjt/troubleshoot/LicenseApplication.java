package com.hcjt.troubleshoot;

import java.util.Arrays;

/**
 * You are given the Licensing logic for a legacy application. The developer who
 * initially developed this logic had left the organization. An intern messed up
 * the licensing logic. Now even valid licenses are being shown as invalid
 * licenses. Sadly, we do not have version control for the code, hence, we could
 * not revert the code to its original working state.
 * 
 * Since the licensing logic needs to be as secure as possible, the variables
 * are all obfuscated and have very minimal or no comments mentioning how the
 * licensing work. You are supposed to figure out the licensing logic and also
 * fix the bug in the code.
 */
public class LicenseApplication {

	public static class License {
		String license;

		public License(String aLicense) {
			this.license = aLicense;
		}

		public boolean validateLicense() {
			String[] licenseBits = license.split("-");

			int[] a = Arrays.stream(licenseBits).mapToInt(s -> {
				return Integer.parseInt(s);
			}).toArray();

			int n = licenseBits.length;

			for (int i = 2; i <= n; i++) {
				if ((a[i] + a[i - 2]) != a[i]) {
					return false;
				}
			}

			return true;
		}

	}

	public static class LicenseTester {

		public void runAllTests() {
			testInvalidLicenseValidationShortLicense();
			testInvalidLicenseValidationWrongLicense();
			testValidLicenseValidation();
		}

		private void testInvalidLicenseValidationShortLicense() {
			invokeTest("1245-1545", false);
		}

		private void testInvalidLicenseValidationWrongLicense() {
			invokeTest("1245-1545-2790-4225-7115", false);
		}

		private void testValidLicenseValidation() {
			invokeTest("1245-1545-2790-4335-7125", true);
		}

		private void invokeTest(String licenseString, boolean expectedResult) {
			License validator = new License(licenseString);
			boolean actualResult = false;
			try {
				actualResult = validator.validateLicense();
			} catch (Exception e) {
				System.out.println("License Validation Error!");
				return;
			}
			if (actualResult == expectedResult) {
				System.out.println("License Validation Passed!");
			} else {
				System.out.println("License Validation Failed!");
			}
		}
	}

	public static void main(String[] args) {
		LicenseTester tester = new LicenseTester();
		tester.runAllTests();
	}
}
