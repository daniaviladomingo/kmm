//
//  BaseView.swift
//  iosApp
//
//  Created by Daniel Ávila on 25/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ManagementResourceState<T : AnyObject, SuccessView : View>: View {
    private let resourceState: BasicUiState
    private let successView: (T?) -> SuccessView
    private let onTryAgain: () -> Void
    private let msgTryAgain: String
    private let onCheckAgain: () -> Void
    private let msgCheckAgain: String

    init(
        resourceState: BasicUiState,
        successView:  @escaping (T?) -> SuccessView,
        onTryAgain: @escaping () -> Void,
        msgTryAgain: String = "An error has ocurred",
        onCheckAgain: @escaping () -> Void,
        msgCheckAgain: String = "No data to show"
    ) {
        self.resourceState = resourceState
        self.successView = successView
        self.onTryAgain = onTryAgain
        self.msgTryAgain = msgTryAgain
        self.onCheckAgain = onCheckAgain
        self.msgCheckAgain = msgCheckAgain
    }
    
    var body: some View {
        switch self.resourceState {
            case is BasicUiStateEmpty:
                EmptyView(msg: self.msgCheckAgain, onClick: self.onCheckAgain)
            case is BasicUiStateError:
                ErrorView(msg: self.msgTryAgain, onClick: self.onTryAgain)
            case is BasicUiStateLoading:
                LoadingView()
            case let success as BasicUiStateSuccess<T>:
                successView(success.data)
            default:
                Text("You shouldn't be seeing this message")
        }
    }
}
