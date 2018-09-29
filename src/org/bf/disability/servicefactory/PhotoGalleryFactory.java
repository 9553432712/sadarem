/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.PhotoGalleryImpl;



/**
 *
 * @author 484898
 */
public class PhotoGalleryFactory {

    public static PhotoGalleryImpl photoGalleryImpl;

    public static PhotoGalleryImpl getPhotoGalleryImpl() {
        if (photoGalleryImpl == null) {
            photoGalleryImpl = new PhotoGalleryImpl();
        }
        return photoGalleryImpl;
    }
}
