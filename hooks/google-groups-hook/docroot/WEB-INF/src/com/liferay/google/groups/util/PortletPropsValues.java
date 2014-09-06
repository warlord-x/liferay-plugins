/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.google.groups.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Matthew Kong
 */
public class PortletPropsValues {

	public static final String GOOGLE_GROUPS_EMAIL_PERMISSION =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.GOOGLE_GROUPS_EMAIL_PERMISSION));

	public static final String GOOGLE_GROUPS_EMAIL_PREFIX =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.GOOGLE_GROUPS_EMAIL_PREFIX));

	public static final boolean GOOGLE_GROUPS_SYNC_ON_STARTUP =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.GOOGLE_GROUPS_SYNC_ON_STARTUP));

}