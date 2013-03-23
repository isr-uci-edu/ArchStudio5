package org.archstudio.xadl.bna.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.xml.xpath.XPathException;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTPackageMetadata;
import org.archstudio.xarchadt.IXArchADTSubstitutionHint;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ui.PlatformUI;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;

public class XArchADTOperations implements IXArchADT {

	public static <V> void remove(String label, final IXArchADT xarch, final ObjRef objRef, final String name,
			final Serializable value) {

		xarch.remove(objRef, name, value);

		IUndoContext undoContext = PlatformUI.getWorkbench().getOperationSupport().getUndoContext();
		IOperationHistory operationHistory = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
		AbstractOperation operation = new AbstractOperation(label) {

			@Override
			public IStatus execute(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				return Status.OK_STATUS;
			}

			@Override
			public IStatus undo(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				xarch.add(objRef, name, value);
				return Status.OK_STATUS;
			}

			@Override
			public IStatus redo(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				xarch.remove(objRef, name, value);
				return Status.OK_STATUS;
			}
		};
		operation.addContext(undoContext);
		operationHistory.add(operation);
	}

	public static <V> void add(String label, final IXArchADT xarch, final ObjRef objRef, final String name,
			final Serializable value) {

		xarch.add(objRef, name, value);

		IUndoContext undoContext = PlatformUI.getWorkbench().getOperationSupport().getUndoContext();
		IOperationHistory operationHistory = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
		AbstractOperation operation = new AbstractOperation(label) {

			@Override
			public IStatus execute(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				return Status.OK_STATUS;
			}

			@Override
			public IStatus undo(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				xarch.remove(objRef, name, value);
				return Status.OK_STATUS;
			}

			@Override
			public IStatus redo(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				xarch.add(objRef, name, value);
				return Status.OK_STATUS;
			}
		};
		operation.addContext(undoContext);
		operationHistory.add(operation);
	}

	public static <V> void set(String label, final IXArchADT xarch, final ObjRef objRef, final String name,
			@Nullable final Serializable newValue) {

		final Serializable oldValue = xarch.get(objRef, name);
		xarch.set(objRef, name, newValue);

		IUndoContext undoContext = PlatformUI.getWorkbench().getOperationSupport().getUndoContext();
		IOperationHistory operationHistory = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
		AbstractOperation operation = new AbstractOperation(label) {

			@Override
			public IStatus execute(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				return Status.OK_STATUS;
			}

			@Override
			public IStatus undo(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				if (oldValue == null) {
					xarch.clear(objRef, name);
				}
				else {
					xarch.set(objRef, name, oldValue);
				}
				return Status.OK_STATUS;
			}

			@Override
			public IStatus redo(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				if (newValue == null) {
					xarch.clear(objRef, name);
				}
				else {
					xarch.set(objRef, name, newValue);
				}
				return Status.OK_STATUS;
			}
		};
		operation.addContext(undoContext);
		operationHistory.add(operation);
	}

	IXArchADT xarch;
	List<Runnable> undo = Lists.newArrayList();
	List<Runnable> redo = Lists.newArrayList();

	public XArchADTOperations(IXArchADT xarch) {
		super();
		this.xarch = xarch;
	}

	@Override
	@Nullable
	public ObjRef lookupObjRefUID(long uid) {
		return xarch.lookupObjRefUID(uid);
	}

	@Override
	public boolean isValidObjRef(ObjRef ref) {
		return xarch.isValidObjRef(ref);
	}

	@Override
	public ObjRef createDocument(URI uri) {
		return xarch.createDocument(uri);
	}

	@Override
	public ObjRef createDocument(URI uri, String nsURI) {
		return xarch.createDocument(uri, nsURI);
	}

	@Override
	@Nullable
	public Serializable get(ObjRef baseObjRef, String typeOfThing) {
		return xarch.get(baseObjRef, typeOfThing);
	}

	@Override
	public ObjRef load(URI uri) throws SAXException, IOException {
		return xarch.load(uri);
	}

	@Override
	public ObjRef load(URI uri, byte[] content) throws SAXException, IOException {
		return xarch.load(uri, content);
	}

	@Override
	public void save(URI uri) throws IOException {
		xarch.save(uri);
	}

	@Override
	public void close(URI uri) {
		xarch.close(uri);
	}

	@Override
	public void add(final ObjRef baseObjRef, final String typeOfThing, final Serializable thingToAddRef) {
		xarch.add(baseObjRef, typeOfThing, thingToAddRef);
		undo.add(new Runnable() {
			@Override
			public void run() {
				xarch.remove(baseObjRef, typeOfThing, thingToAddRef);
			}
		});
		redo.add(new Runnable() {
			@Override
			public void run() {
				xarch.add(baseObjRef, typeOfThing, thingToAddRef);
			}
		});
	}

	@Override
	public void add(final ObjRef baseObjRef, final String typeOfThing,
			final Collection<? extends Serializable> thingsToAddRefs) {
		final List<? extends Serializable> fThingsToAddRefs = Lists.newArrayList(thingsToAddRefs);
		xarch.add(baseObjRef, typeOfThing, fThingsToAddRefs);
		undo.add(new Runnable() {
			@Override
			public void run() {
				xarch.remove(baseObjRef, typeOfThing, fThingsToAddRefs);
			}
		});
		redo.add(new Runnable() {
			@Override
			public void run() {
				xarch.add(baseObjRef, typeOfThing, fThingsToAddRefs);
			}
		});
	}

	@Override
	public void clear(final ObjRef baseObjRef, final String typeOfThing) {
		final Serializable oldValue = xarch.get(baseObjRef, typeOfThing);
		xarch.clear(baseObjRef, typeOfThing);
		undo.add(new Runnable() {
			@Override
			public void run() {
				xarch.set(baseObjRef, typeOfThing, oldValue);
			}
		});
		redo.add(new Runnable() {
			@Override
			public void run() {
				xarch.clear(baseObjRef, typeOfThing);
			}
		});
	}

	@Override
	public void remove(final ObjRef baseObjRef, final String typeOfThing, final Serializable valueToRemove) {
		xarch.remove(baseObjRef, typeOfThing, valueToRemove);
		undo.add(new Runnable() {
			@Override
			public void run() {
				xarch.add(baseObjRef, typeOfThing, valueToRemove);
			}
		});
		redo.add(new Runnable() {
			@Override
			public void run() {
				xarch.remove(baseObjRef, typeOfThing, valueToRemove);
			}
		});
	}

	@Override
	@Nullable
	public Serializable resolve(ObjRef objRef) {
		return xarch.resolve(objRef);
	}

	@Override
	public List<Serializable> getAll(ObjRef baseObjRef, String typeOfThing) {
		return xarch.getAll(baseObjRef, typeOfThing);
	}

	@Override
	public void remove(final ObjRef baseObjRef, final String typeOfThing,
			Collection<? extends Serializable> thingsToRemove) {
		final List<? extends Serializable> fThingsToRemove = Lists.newArrayList(thingsToRemove);
		xarch.remove(baseObjRef, typeOfThing, fThingsToRemove);
		undo.add(new Runnable() {
			@Override
			public void run() {
				xarch.add(baseObjRef, typeOfThing, fThingsToRemove);
			}
		});
		redo.add(new Runnable() {
			@Override
			public void run() {
				xarch.remove(baseObjRef, typeOfThing, fThingsToRemove);
			}
		});
	}

	@Override
	public boolean hasAncestor(ObjRef childRef, ObjRef ancestorRef) {
		return xarch.hasAncestor(childRef, ancestorRef);
	}

	@Override
	public boolean isAttached(ObjRef childRef) {
		return xarch.isAttached(childRef);
	}

	@Override
	public List<ObjRef> getAllAncestors(ObjRef targetObjRef) {
		return xarch.getAllAncestors(targetObjRef);
	}

	@Override
	public void set(final ObjRef baseObjRef, final String typeOfThing, @Nullable final Serializable value) {
		final Serializable oldValue = xarch.get(baseObjRef, typeOfThing);
		xarch.set(baseObjRef, typeOfThing, value);
		undo.add(new Runnable() {
			@Override
			public void run() {
				xarch.set(baseObjRef, typeOfThing, oldValue);
			}
		});
		redo.add(new Runnable() {
			@Override
			public void run() {
				xarch.set(baseObjRef, typeOfThing, value);
			}
		});
	}

	@Override
	public List<ObjRef> getLineage(ObjRef targetObjRef) {
		return xarch.getLineage(targetObjRef);
	}

	@Override
	@Nullable
	public ObjRef getParent(ObjRef targetObjRef) {
		return xarch.getParent(targetObjRef);
	}

	@Override
	@Nullable
	public ObjRef getByID(ObjRef documentRootRef, String id) {
		return xarch.getByID(documentRootRef, id);
	}

	@Override
	public ObjRef create(String nsURI, String typeOfThing) {
		return xarch.create(nsURI, typeOfThing);
	}

	@Override
	public void renameXArch(String oldURI, String newURI) {
		xarch.renameXArch(oldURI, newURI);
	}

	@Override
	@Nullable
	public ObjRef getByID(String id) {
		return xarch.getByID(id);
	}

	@Override
	@Nullable
	public ObjRef resolveHref(ObjRef documentRootRef, String href) {
		return xarch.resolveHref(documentRootRef, href);
	}

	@Override
	@Nullable
	public ObjRef getDocumentRootRef(ObjRef ref) {
		return xarch.getDocumentRootRef(ref);
	}

	@Override
	@Nullable
	public String getTagName(ObjRef ref) {
		return xarch.getTagName(ref);
	}

	@Override
	@Nullable
	public String getContainingFeatureName(ObjRef ref) {
		return xarch.getContainingFeatureName(ref);
	}

	@Override
	public String getTagsOnlyPathString(ObjRef ref) {
		return xarch.getTagsOnlyPathString(ref);
	}

	@Override
	public IXArchADTPackageMetadata getPackageMetadata(String nsURI) {
		return xarch.getPackageMetadata(nsURI);
	}

	@Override
	public Collection<IXArchADTPackageMetadata> getAvailablePackageMetadata() {
		return xarch.getAvailablePackageMetadata();
	}

	@Override
	public IXArchADTTypeMetadata getTypeMetadata(String nsURI, String typeName) {
		return xarch.getTypeMetadata(nsURI, typeName);
	}

	@Override
	public IXArchADTTypeMetadata getTypeMetadata(ObjRef objRef) {
		return xarch.getTypeMetadata(objRef);
	}

	@Override
	public boolean isInstanceOf(@Nullable ObjRef baseObjRef, String sourceNsURI, String sourceTypeName) {
		return xarch.isInstanceOf(baseObjRef, sourceNsURI, sourceTypeName);
	}

	@Override
	public boolean isAssignable(String sourceNsURI, String sourceTypeName, String targetNsURI, String targetTypeName) {
		return xarch.isAssignable(sourceNsURI, sourceTypeName, targetNsURI, targetTypeName);
	}

	@Override
	public List<IXArchADTSubstitutionHint> getAllSubstitutionHints() {
		return xarch.getAllSubstitutionHints();
	}

	@Override
	public List<IXArchADTSubstitutionHint> getSubstitutionHintsForSource(String sourceNsURI, String sourceTypeName) {
		return xarch.getSubstitutionHintsForSource(sourceNsURI, sourceTypeName);
	}

	@Override
	public List<IXArchADTSubstitutionHint> getSubstitutionHintsForTarget(String targetNsURI, String targetTypeName) {
		return xarch.getSubstitutionHintsForTarget(targetNsURI, targetTypeName);
	}

	@Override
	@Nullable
	public ObjRef getDocumentRootRef(URI uri) {
		return xarch.getDocumentRootRef(uri);
	}

	@Override
	public Collection<URI> getOpenURIs() {
		return xarch.getOpenURIs();
	}

	@Override
	@Nullable
	public URI getURI(ObjRef ref) {
		return xarch.getURI(ref);
	}

	@Override
	public byte[] serialize(URI uri) {
		return xarch.serialize(uri);
	}

	@Override
	public List<ObjRef> resolveObjRefs(ObjRef contextObjRef, String xPath) throws XPathException {
		return xarch.resolveObjRefs(contextObjRef, xPath);
	}

	@Override
	public List<Serializable> resolveSerializables(ObjRef contextObjRef, String xPath) throws XPathException {
		return xarch.resolveSerializables(contextObjRef, xPath);
	}

	@Override
	public String getXPath(ObjRef toObjRef) {
		return xarch.getXPath(toObjRef);
	}

	public void done(String label) {
		IUndoContext undoContext = PlatformUI.getWorkbench().getOperationSupport().getUndoContext();
		IOperationHistory operationHistory = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
		AbstractOperation operation = new AbstractOperation(label) {

			@Override
			public IStatus execute(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				return Status.OK_STATUS;
			}

			@Override
			public IStatus undo(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				for (Runnable r : undo) {
					r.run();
				}
				return Status.OK_STATUS;
			}

			@Override
			public IStatus redo(@Nullable IProgressMonitor monitor, @Nullable IAdaptable info)
					throws ExecutionException {
				for (Runnable r : redo) {
					r.run();
				}
				return Status.OK_STATUS;
			}
		};
		operation.addContext(undoContext);
		operationHistory.add(operation);
	}
}
