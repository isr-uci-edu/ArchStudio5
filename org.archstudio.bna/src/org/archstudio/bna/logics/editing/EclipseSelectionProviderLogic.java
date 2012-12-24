package org.archstudio.bna.logics.editing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchSite;

import com.google.common.collect.Lists;

public abstract class EclipseSelectionProviderLogic extends AbstractThingLogic implements IBNAModelListener {

	private static class WorkbenchSiteSelectionProvider implements ISelectionProvider {

		private final IWorkbenchSite workbenchSite;

		public WorkbenchSiteSelectionProvider(IWorkbenchSite workbenchSite) {
			this.workbenchSite = workbenchSite;
		}

		private final CopyOnWriteArrayList<EclipseSelectionProviderLogic> allEclipseSelectionProviderLogics = Lists
				.newCopyOnWriteArrayList();

		public void addEclipseSelectionProvider(EclipseSelectionProviderLogic eclipseSelectionProviderLogic) {
			allEclipseSelectionProviderLogics.add(eclipseSelectionProviderLogic);
		}

		public void removeEclipseSelectionProvider(EclipseSelectionProviderLogic eclipseSelectionProviderLogic) {
			allEclipseSelectionProviderLogics.remove(eclipseSelectionProviderLogic);
		}

		private final CopyOnWriteArrayList<ISelectionChangedListener> selectionChangedListeners = Lists
				.newCopyOnWriteArrayList();

		@Override
		public void addSelectionChangedListener(ISelectionChangedListener listener) {
			selectionChangedListeners.add(listener);
		}

		@Override
		public void removeSelectionChangedListener(ISelectionChangedListener listener) {
			selectionChangedListeners.remove(listener);
		}

		public void fireSelectionChangedEvent() {
			final SelectionChangedEvent evt = new SelectionChangedEvent(this, getSelection());

			SWTWidgetUtils.async(workbenchSite.getShell(), new Runnable() {
				@Override
				public void run() {
					for (ISelectionChangedListener l : selectionChangedListeners) {
						l.selectionChanged(evt);
					}
				}
			});
		}

		private final Collection<Object> selection = Collections.synchronizedCollection(new ArrayList<Object>());

		@Override
		public ISelection getSelection() {
			return new StructuredSelection(selection.toArray());
		}

		@Override
		public void setSelection(ISelection selection) {
			// TODO: not sure how to support this
		}

		public void setSelection(EclipseSelectionProviderLogic eclipseSelectionProviderLogic, Object[] selectedObjects) {
			for (EclipseSelectionProviderLogic l : allEclipseSelectionProviderLogics) {
				if (l != eclipseSelectionProviderLogic) {
					l._unselectAll();
				}
			}
			if (!Arrays.equals(selectedObjects, selection.toArray(new Object[selection.size()]))) {
				selection.clear();
				selection.addAll(Arrays.asList(selectedObjects));
				fireSelectionChangedEvent();
			}
		}
	}

	private final WorkbenchSiteSelectionProvider workbenchSiteSelectionProvider;

	public EclipseSelectionProviderLogic(IWorkbenchSite workbenchSite) {
		if (workbenchSite.getSelectionProvider() == null) {
			workbenchSite.setSelectionProvider(workbenchSiteSelectionProvider = new WorkbenchSiteSelectionProvider(
					workbenchSite));
		}
		else if (workbenchSite.getSelectionProvider() instanceof WorkbenchSiteSelectionProvider) {
			workbenchSiteSelectionProvider = (WorkbenchSiteSelectionProvider) workbenchSite.getSelectionProvider();
		}
		else {
			throw new RuntimeException(
					"EclipseSelectionProviderLogic cannot register itself as the selection provider.");
		}
	}

	@Override
	protected void init() {
		super.init();
		workbenchSiteSelectionProvider.addEclipseSelectionProvider(this);
	}

	@Override
	protected void destroy() {
		workbenchSiteSelectionProvider.removeEclipseSelectionProvider(this);
		super.destroy();
	}

	private static final String BEGIN_IGNORING_SELECTION_EVENTS_NOTIFICATION = EclipseSelectionProviderLogic.class
			.getName() + ":BeginIgnoringSelectionEvents";
	private static final String END_IGNORING_SELECTION_EVENTS_NOTIFICATION = EclipseSelectionProviderLogic.class
			.getName() + ":EndIgnoringSelectionEvents";
	private int inBulkChange = 0;
	private int ignoreSelection = 0;

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case BULK_CHANGE_BEGIN:
			inBulkChange++;
			break;

		case BULK_CHANGE_END:
			if (--inBulkChange <= 0) {
				inBulkChange = 0;
			}
			break;

		case STREAM_NOTIFICATION_EVENT:
			if (BEGIN_IGNORING_SELECTION_EVENTS_NOTIFICATION.equals(evt.getStreamNotification())) {
				ignoreSelection++;
			}
			else if (END_IGNORING_SELECTION_EVENTS_NOTIFICATION.equals(evt.getStreamNotification())) {
				if (--ignoreSelection <= 0) {
					ignoreSelection = 0;
				}
			}
		default:
			// do nothing
		}
	}

	private void _unselectAll() {
		IBNAModel model = getBNAModel();
		if (model != null) {
			model.fireStreamNotificationEvent(BEGIN_IGNORING_SELECTION_EVENTS_NOTIFICATION);
			unselectAll();
			model.fireStreamNotificationEvent(END_IGNORING_SELECTION_EVENTS_NOTIFICATION);
		}
	}

	abstract protected void unselectAll();

	protected void setSelection(Object[] selectedObjects) {
		if (ignoreSelection == 0) {
			workbenchSiteSelectionProvider.setSelection(this, selectedObjects);
		}
	}
}
