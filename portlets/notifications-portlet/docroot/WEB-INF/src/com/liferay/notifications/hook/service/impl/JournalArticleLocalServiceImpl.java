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

package com.liferay.notifications.hook.service.impl;

import com.liferay.compat.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.notifications.util.NotificationsUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalService;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceWrapper;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Lin Cui
 */
public class JournalArticleLocalServiceImpl
	extends JournalArticleLocalServiceWrapper {

	public JournalArticleLocalServiceImpl(
		JournalArticleLocalService journalArticleLocalService) {

		super(journalArticleLocalService);
	}

	@Override
	public JournalArticle updateStatus(
			long userId, JournalArticle article, int status, String articleURL,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalArticle journalArticle = super.updateStatus(
			userId, article, status, articleURL, workflowContext,
			serviceContext);

		int notificationType =
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY;

		if (serviceContext.isCommandUpdate()) {
			notificationType =
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY;
		}

		String entryURL = NotificationsUtil.getEntryURL(
			_assetRendererFactory.getAssetRenderer(article.getId()),
			serviceContext);

		NotificationsUtil.sendNotificationEvent(
			article.getCompanyId(), _JOURNAL_ARTICLE_CLASS_NAME,
			article.getGroupId(), PortletKeys.JOURNAL,
			_JOURNAL_ARTICLE_CLASS_NAME, article.getId(), article.getTitle(),
			entryURL, notificationType, userId);

		return journalArticle;
	}

	protected AssetRendererFactory _assetRendererFactory =
		AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
			_JOURNAL_ARTICLE_CLASS_NAME);

	private static final String _JOURNAL_ARTICLE_CLASS_NAME =
		JournalArticle.class.getName();

}