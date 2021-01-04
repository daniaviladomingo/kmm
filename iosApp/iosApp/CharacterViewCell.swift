//
//  CharacterViewCell.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 04/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class CharacterViewCell: UITableViewCell {
    var character: Character? {
        didSet {
            imageCharacter.load(url: URL(string: character!.image)!)
            name.text = character?.name
        }
    }
    
    let imageCharacter: UIImageView = {
        let img = UIImageView()
        img.contentMode = .scaleAspectFill
        img.translatesAutoresizingMaskIntoConstraints = false
        img.clipsToBounds = true
        return img
    }()
    
    let name: UILabel = {
        let label = UILabel()
        label.font = UIFont.boldSystemFont(ofSize: 20)
        label.textColor = .black
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        
        self.contentView.backgroundColor = .white
        
        self.contentView.addSubview(imageCharacter)
        self.contentView.addSubview(name)
                
        imageCharacter.centerYAnchor.constraint(equalTo: self.contentView.centerYAnchor).isActive = true
        imageCharacter.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor, constant: 10).isActive = true
        imageCharacter.widthAnchor.constraint(equalToConstant: 80).isActive = true
        imageCharacter.heightAnchor.constraint(equalToConstant: 80).isActive = true
        
        name.centerYAnchor.constraint(equalTo: contentView.centerYAnchor).isActive = true
        name.leadingAnchor.constraint(equalTo: imageCharacter.trailingAnchor, constant: 20).isActive = true
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
}

